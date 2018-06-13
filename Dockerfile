FROM daocloud.io/ld00000/jdk-8:latest

# ------------------------------------------------------------------------------------------
#
#                                     安装微服务应用customer
#
# ------------------------------------------------------------------------------------------
#安装应用
RUN mkdir -p /etc/myteay/plateform/

ADD customer-biz-service-impl-1.0.0.jar  /etc/myteay/plateform/customer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/etc/myteay/plateform/customer.jar"]

#挂载安装应用所需的日志路径
VOLUME /etc/myteay/plateform/logs/
VOLUME /etc/myteay/plateform/qrcode/usercode/
# PORT
EXPOSE 40001

