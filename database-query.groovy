pipeline {
  agent { label 'docker/mysql8-client'}
  environment {
    MYSQL_CREDS=credentials('mysql-user')
  }
  parameters {
    choice(name: 'ISO_CODE', choices: ['US', 'AU', 'DE'], description: 'Select ISO code')
  }
  stages {
    stage('query') {
      steps {
        sh(script:'''
          echo -e "[client]\nuser=$MYSQL_CREDS_USR\npassword=$MYSQL_CREDS_PSW\nhost=malik.host\ndatabase=scratch" | mysql --defaults-file=/dev/stdin -N -e "select json_object('iso',iso,'country_name',country_name,'currency_code',currency_code) from countries where iso=\\\"$ISO_CODE\\\"" | jq -r ".country_name"
        ''')
      }
    }
  }
}
