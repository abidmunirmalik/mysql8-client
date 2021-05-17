FROM centos:7
RUN rpm -Uvh https://repo.mysql.com/mysql80-community-release-el7-3.noarch.rpm  && \
    sed -i 's/enabled=1/enabled=0/' /etc/yum.repos.d/mysql-community.repo && \
    yum -y --enablerepo=mysql80-community install mysql-community-client
