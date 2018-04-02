package com.org.datasource; 
/**
 * @author yebing
 * ʹ��ThreadLocal��������¼��ǰ�߳��е�����Դ��key 
 */
public class DynamicDataSourceHolder {  
      
    //д���Ӧ������Դkey  
    private static final String MASTER = "master";  
    //�����Ӧ������Դkey  
    private static final String SLAVE = "slave";  
    //ʹ��ThreadLocal��¼��ǰ�̵߳�����Դkey  
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();  
  
    /** 
     * ��������Դkey 
     * @param key 
     */  
    public static void putDataSourceKey(String key) {  
        holder.set(key);  
    }  
  
    /** 
     * ��ȡ����Դkey 
     * @return 
     */  
    public static String getDataSourceKey() { 
        return holder.get();  
    }  
    
    /** 
     * ���д�� 
     */  
    public static void markMaster(){  
        putDataSourceKey(MASTER);  
    }  
      
    /** 
     * ��Ƕ��� 
     */  
    public static void markSlave(){  
        putDataSourceKey(SLAVE);  
    }  
  
}  