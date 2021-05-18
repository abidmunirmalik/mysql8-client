FROM centos:7
RUN yum -y install epel-release java-1.8.0-openjdk && yum -y install jq && \
    rpm -Uvh https://repo.mysql.com/mysql80-community-release-el7-3.noarch.rpm  && \
    sed -i 's/enabled=1/enabled=0/' /etc/yum.repos.d/mysql-community.repo && \
    yum -y --enablerepo=mysql80-community install mysql-community-client
