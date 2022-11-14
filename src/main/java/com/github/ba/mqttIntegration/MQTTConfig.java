//package com.github.ba.mqttIntegration;
//
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.endpoint.MessageProducerSupport;
//import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
//import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
//import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
//import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
//
///**
// * @author wang xiao
// * date 2022/11/14
// */
//public class MQTTConfig implements ApplicationListener<ApplicationEvent> {
//
//    // //发布消息到消息中间件接口
//    private final MsgSendService msgSendService;
//
//    @Value("${mqtt.appid:mqtt_id}")
//    private String appid;//客户端ID
//
//    @Value("${mqtt.input.topic:mqtt_input_topic}")
//    private String[] inputTopic;//订阅主题，可以是多个主题
//
//    @Value("${mqtt.out.topic:mqtt_out_topic}")
//    private String[] outTopic;//发布主题，可以是多个主题
//
//    @Value("${mqtt.services:#{null}}")
//    private String[] mqttServices;//服务器地址以及端口
//
//    @Value("${mqtt.user:#{null}}")
//    private String user;
//
//
//    @Value("${mqtt.password:#{null}}")
//    private String password;//密码
//
//    @Value("${mqtt.KeepAliveInterval:300}")
//    private Integer KeepAliveInterval;//心跳时间,默认为5分钟
//
//    @Value("${mqtt.CleanSession:false}")
//    private Boolean CleanSession;//是否不保持session,默认为session保持
//
//    @Value("${mqtt.AutomaticReconnect:true}")
//    private Boolean AutomaticReconnect;//是否自动重联，默认为开启自动重联
//
//    @Value("${mqtt.CompletionTimeout:30000}")
//    private Long CompletionTimeout;//连接超时,默认为30秒
//
//    @Value("${mqtt.Qos:1}")
//    private Integer Qos;//通信质量，详见MQTT协议
//
//
//    public MQTTConfig(MsgSendService msgSendService) {
//        this.msgSendService = msgSendService;
//    }
//
//    /**
//     * MQTT连接配置
//     * @return 连接工厂
//     */
//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();//连接工厂类
//        MqttConnectOptions options = new MqttConnectOptions();//连接参数
//        options.setServerURIs(mqttServices);//连接地址
//        if(null!=user) {
//            options.setUserName(user);//用户名
//        }
//        if(null!=password) {
//            options.setPassword(password.toCharArray());//密码
//        }
//        options.setKeepAliveInterval(KeepAliveInterval);//心跳时间
//        options.setAutomaticReconnect(AutomaticReconnect);//断开是否自动重联
//        options.setCleanSession(CleanSession);//保持session
//        factory.setConnectionOptions(options);
//        return factory;
//    }
//
//
//    /**
//     * 入站管道
//     * @param mqttPahoClientFactory
//     * @return
//     */
//    @Bean
//    public MessageProducerSupport mqttInput(MqttPahoClientFactory mqttPahoClientFactory){
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(appid, mqttPahoClientFactory, inputTopic);//建立订阅连接
//        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
//        converter.setPayloadAsBytes(true);//bytes类型接收
//        adapter.setCompletionTimeout(CompletionTimeout);//连接超时的时间
//        adapter.setConverter(converter);
//        adapter.setQos(Qos);//消息质量
//        adapter.setOutputChannelName("INPUT_DATA");//输入管道名称
//        return adapter;
//    }
//
//
//}
