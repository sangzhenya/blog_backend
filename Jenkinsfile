pipeline {
  agent any
  stages {
    stage('Start') {
      steps {
        echo 'End start'

        dir(path: '/home/ubuntu/.jenkins/workspace/blog_backend_master/src/main/resource') {
          sh 'cp /home/ubuntu/resource/application-prd.yaml .'
        }
      }
    }

    stage('Build ') {
      steps {
        dir(path: '/home/ubuntu/.jenkins/workspace/blog_backend_master') {
          sh 'mvn clean package -Dmaven.test.skip=true'
        }
      }
    }

    stage('Docker') {
      steps {
        dir(path: '/home/ubuntu/.jenkins/workspace/blog_backend_master') {
          sh 'sudo docker build -f Dockerfile -t blog  .'
        }

        sh 'sudo docker stop blog || echo "No need stop"'
        sh 'sudo docker rm blog || echo "No need remove"'
        sh 'sudo docker run -p 8004:8004 --name blog -t blog'
      }
    }

    stage('End') {
      steps {
        echo 'End process'
      }
    }

  }
}