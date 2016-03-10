#!/usr/bin/env bash

sudo apt-get update
sudo apt-get install -y apache2 tomcat7
sudo a2enmod proxy proxy_ajp
sudo cp /vagrant/resource/server.xml /etc/tomcat7/
sudo cp /vagrant/resource/000-default.conf /etc/apache2/sites-enabled/
sudo service tomcat7 restart
sudo service apache2 restart
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password rootpass'
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password rootpass'
sudo apt-get install -y mysql-server
mysql -u root -prootpass -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '' WITH GRANT OPTION; FLUSH PRIVILEGES;"
