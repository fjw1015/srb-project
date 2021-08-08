# srb-project
尚融宝项目学习（springboot + vue）。只需要启动数据库  + redis + nacos，需要启动hfb项目（相当于第三方接口），并且在最后再启动gateway网关，如果有nacos相关报错 删除nacos文件夹下面的data文件夹（ip设置为DHCP后导致ip地址不定）
# 前端运行
npm run dev（建议再此之前进行npm install）
# rabbitMQ
我使用虚拟机，如果要使用需要修改yml文件中的MQ ip地址，用户名和密码，使用MQ需要记得关闭防火墙
# 数据库
本次使用的数据库是MYSQL5.7 如果低于需要修改相应的yml配置文件的driver，在使用的时候还要记得修改数据库名称，数据库用户名和密码
# SMS 与 OSS
SMS因为个人用户原因，没有办法申请到验证码的使用资格，OSS免费使用，建议使用自己的账号（在阿里云申请）
