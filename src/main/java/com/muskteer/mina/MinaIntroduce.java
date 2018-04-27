package com.muskteer.mina;

public class MinaIntroduce {
/*
 * 
 * Apache Mina Server 是一个网络通信应用框架，
 * 它主要是对基于TCP/IP、UDP/IP协议栈的通信框架（当然，也可以提供Java 对象的序列化服务、虚拟机管道通信服务等），
 * Mina 可以帮助我们快速开发高性能、高扩展性的网络通信应用，
 * Mina 提供了事件驱动、异步（Mina 的异步IO 默认使用的是Java NIO 作为底层支持）操作的编程模型。
 * 
 * 
 * 
(1.) IoService：这个接口在一个线程上负责套接字的建立，拥有自己的Selector，监听是否有连接被建立。
(2.) IoProcessor：这个接口在另一个线程上，负责检查是否有数据在通道上读写，也就是说它也拥有自己的Selector，这是与我们使用JAVA NIO 编码时的一个不同之处，通常在JAVA NIO 编码中，我们都是使用一个Selector，也就是不区分IoService与IoProcessor 两个功能接口。另外，IoProcessor 负责调用注册在IoService 上的过滤器，并在过滤器链之后调用IoHandler。
(3.) IoFilter：这个接口定义一组拦截器，这些拦截器可以包括日志输出、黑名单过滤、数据的编码（write 方向）与解码（read 方向）等功能，其中数据的encode 与decode是最为重要的、也是你在使用Mina 时最主要关注的地方。
(4.) IoHandler：这个接口负责编写业务逻辑，也就是接收、发送数据的地方。
 * 
 * 
 * 
 */
}
