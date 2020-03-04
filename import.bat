
@echo off

echo 开始提交代码到本地仓库
echo 当前目录是：%cd%
echo ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

mvn eclipse:clean eclipse:eclipse
echo;
echo 批处理执行完毕！
echo;

pause;