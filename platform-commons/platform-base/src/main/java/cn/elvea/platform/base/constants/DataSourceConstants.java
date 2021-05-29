package cn.elvea.platform.base.constants;

/**
 * 数据相关全局变量
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class DataSourceConstants {

    /**
     * 读写分离数据源
     */
    public final static String MASTER_SLAVE_DATASOURCE = "masterSlaveDataSource";

    /**
     * 主数据源
     */
    public final static String MASTER_DATASOURCE = "masterDataSource";

    /**
     * 从数据源
     */
    public final static String SLAVE_DATASOURCE = "slaveDataSource";

    /**
     * 从数据源
     */
    public final static String LOAD_BALANCER = "load_balancer";

}
