# mulDatasource-demo
这是一个多数据源实现读写分离的项目，主数据库负责写，从数据库负责读。代码负责通过AOP实现读写数据库分开，但实际上要做到主数据库、从数据库同步需要在数据库中配置，这里不细说，有需要的可以自己百度配置再结合这个项目。同时此项目也并非自己原创，参考了他人代码，我仅仅理解了核心如果有冲突请见谅。
