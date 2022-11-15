package com.github.ba.mqttIntegration;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

/**
 * @author wang xiao
 * date 2022/11/14
 */
public class MQTTConfig implements ApplicationListener<ApplicationEvent> {



    @Value("${mqtt.appid:mqtt_id}")
    private String appid;

    // 订阅主题
    @Value("${mqtt.input.topic:mqtt_input_topic}")
    private String[] inputTopic;

    // 发布主题
    @Value("${mqtt.out.topic:mqtt_out_topic}")
    private String[] outTopic;

    @Value("${mqtt.services:#{null}}")
    private String[] mqttServices;

    @Value("${mqtt.user:#{null}}")
    private String user;


    @Value("${mqtt.password:#{null}}")
    private String password;

    @Value("${mqtt.KeepAliveInterval:300}")
    private Integer keepAliveInterval;

    @Value("${mqtt.CleanSession:false}")
    private Boolean cleanSession;

    @Value("${mqtt.AutomaticReconnect:true}")
    private Boolean automaticReconnect;

    @Value("${mqtt.CompletionTimeout:30000}")
    private Long completionTimeout;

    @Value("${mqtt.Qos:1}")
    private Integer qos;



    /**
     * MQTT连接配置
     * @return 连接工厂
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(mqttServices);
        if(null!=user) {
            options.setUserName(user);
        }
        if(null!=password) {
            options.setPassword(password.toCharArray());
        }
        options.setKeepAliveInterval(keepAliveInterval);
        options.setAutomaticReconnect(automaticReconnect);
        options.setCleanSession(cleanSession);
        factory.setConnectionOptions(options);
        return factory;
    }


    /**
     * 入站管道
     * @param mqttPahoClientFactory mqttPahoClientFactory
     * @return MessageProducerSupport
     */
    @Bean
    public MessageProducerSupport mqttInput(MqttPahoClientFactory mqttPahoClientFactory){
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(appid, mqttPahoClientFactory, inputTopic);
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true);
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setConverter(converter);
        adapter.setQos(qos);
        adapter.setOutputChannelName("INPUT_DATA");
        return adapter;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

    }
}
