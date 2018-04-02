package com.org.datasource;


import java.lang.reflect.Field;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
  
import org.apache.commons.lang3.StringUtils;  
import org.aspectj.lang.JoinPoint;  
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;  
import org.springframework.transaction.interceptor.TransactionAttribute;  
import org.springframework.transaction.interceptor.TransactionAttributeSource;  
import org.springframework.transaction.interceptor.TransactionInterceptor;  
import org.springframework.util.PatternMatchUtils;  
import org.springframework.util.ReflectionUtils;  
  
/**
 * @author yebing
 * ��������Դ��AOP���棬���������ʹ��Master����Slave�� 
 *  
 * ������������������������ԣ���������õ���������еı����ReadOnly�ķ�������Slave������ʹ��Master�� 
 *  
 * ���û�������������Ĳ��ԣ�����÷�����ƥ���ԭ����query��find��get��ͷ������Slave��������Master�� 
 *  
 */
public class DataSourceAspect {  
    private List<String> slaveMethodPattern = new ArrayList<String>();  
    private static final String[] defaultSlaveMethodStart = new String[]{ "query", "find", "get" };  
    private String[] slaveMethodStart;  
    /** 
     * ��ȡ��������еĲ��� 
     *  
     * @param txAdvice 
     * @throws Exception 
     */  
    public void setTxAdvice(TransactionInterceptor txAdvice) throws Exception {  
        if (txAdvice == null) {  
            // û����������������  
            return;  
        }  
        //��txAdvice��ȡ������������Ϣ  
        TransactionAttributeSource transactionAttributeSource = txAdvice.getTransactionAttributeSource();  
        if (!(transactionAttributeSource instanceof NameMatchTransactionAttributeSource)) {  
            return;  
        }  
        //ʹ�÷��似����ȡ��NameMatchTransactionAttributeSource�����е�nameMap����ֵ  
        NameMatchTransactionAttributeSource matchTransactionAttributeSource = (NameMatchTransactionAttributeSource) transactionAttributeSource;  
        Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");  
        nameMapField.setAccessible(true); //���ø��ֶοɷ���  
        //��ȡnameMap��ֵ  
        Map<String, TransactionAttribute> map = (Map<String, TransactionAttribute>) nameMapField.get(matchTransactionAttributeSource);  
  
        //����nameMap  
        for (Map.Entry<String, TransactionAttribute> entry : map.entrySet()) {  
            if (!entry.getValue().isReadOnly()) {//�ж�֮������ReadOnly�Ĳ��Բż��뵽slaveMethodPattern  
                continue;  
            }  
            slaveMethodPattern.add(entry.getKey());  
        }  
    }  
  
    /** 
     * �ڽ���Service����֮ǰִ�� 
     *  
     * @param point ������� 
     */  
    public void before(JoinPoint point) {  
        // ��ȡ����ǰִ�еķ�����  
        String methodName = point.getSignature().getName();  
  
        boolean isSlave = false;  
  
        if (slaveMethodPattern.isEmpty()) {  
            // ��ǰSpring������û������������ԣ����÷�����ƥ�䷽ʽ  
            isSlave = isSlave(methodName);  
        } else {  
            // ʹ�ò��Թ���ƥ��  
            for (String mappedName : slaveMethodPattern) {  
                if (isMatch(methodName, mappedName)) {  
                    isSlave = true;  
                    break;  
                }  
            }  
        }  
  
        if (isSlave) {  
            // ���Ϊ����  
            DynamicDataSourceHolder.markSlave();  
        } else {  
            // ���Ϊд��  
            DynamicDataSourceHolder.markMaster();  
        }  
    }  
  
    /** 
     * �ж��Ƿ�Ϊ���� 
     *  
     * @param methodName 
     * @return 
     */  
    private Boolean isSlave(String methodName) {  
        // ��������query��find��get��ͷ�ķ������ߴӿ�  
        return StringUtils.startsWithAny(methodName, getSlaveMethodStart());  
    }  
  
    /** 
     * ͨ���ƥ�� 
     *  
     * Return if the given method name matches the mapped name. 
     * <p> 
     * The default implementation checks for "xxx*", "*xxx" and "*xxx*" matches, as well as direct 
     * equality. Can be overridden in subclasses. 
     *  
     * @param methodName the method name of the class 
     * @param mappedName the name in the descriptor 
     * @return if the names match 
     * @see org.springframework.util.PatternMatchUtils#simpleMatch(String, String) 
     */  
    protected boolean isMatch(String methodName, String mappedName) {  
        return PatternMatchUtils.simpleMatch(mappedName, methodName);  
    }  
  
    /** 
     * �û�ָ��slave�ķ�����ǰ׺ 
     * @param slaveMethodStart 
     */  
    public void setSlaveMethodStart(String[] slaveMethodStart) {  
        this.slaveMethodStart = slaveMethodStart;  
    }  
  
    public String[] getSlaveMethodStart() {  
        if(this.slaveMethodStart == null){  
            // û��ָ����ʹ��Ĭ��  
            return defaultSlaveMethodStart;  
        }  
        return slaveMethodStart;  
    }  
      
}  