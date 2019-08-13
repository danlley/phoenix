FROM tiancan_jdk_ubuntu_201908130128

# ------------------------------------------------------------------------------------------
#
#                                     安装微服务应用phoenix
# docker run -p 40009:40009 -p 8003:50005 -v /home/danlley/docker/share/phoenix/logs:/etc/myteay/plateform/logs/phoenix/ -v /home/danlley/docker/share/phoenix/file:/etc/myteay/plateform/phoenix/images -v /etc/localtime:/etc/localtime -d
phoenix_201905102157
#
# ------------------------------------------------------------------------------------------
#安装应用
RUN mkdir -p /etc/myteay/plateform/
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD phoenix-biz-service-impl-1.0.0.jar  /etc/myteay/plateform/phoenix.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/etc/myteay/plateform/phoenix.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=utf-8","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=50005","-Dspring.profiles.active=sit","/etc/myteay/plateform/phoenix.jar"]

#挂载安装应用所需的日志路径
VOLUME /etc/myteay/plateform/logs/
VOLUME /etc/myteay/plateform/phoenix/images/

# PORT
EXPOSE 40009
EXPOSE 50005

