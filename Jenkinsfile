pipeline {
  agent any
  stages {
    stage('Start') {
      steps {
        echo 'End start'
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
        sh 'sudo docker stop blog'
        sh 'sudo docker rm blog'
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