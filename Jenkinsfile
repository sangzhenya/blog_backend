pipeline {
  agent any
  stages {
    stage('Git Pull') {
      steps {
        dir(path: '/home/ubuntu/docker/blog_backend/') {
          git 'https://github.com/sangzhenya/blog_backend.git'
        }

        echo 'Start process'
      }
    }

    stage('Build ') {
      steps {
        dir(path: '/home/ubuntu/docker/blog_backend') {
          sh 'mvn clean package -Dmaven.test.skip=true'
        }

      }
    }

    stage('Docker') {
      steps {
        sh 'sudo docker build -f Dockerfile -t blog  .'
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