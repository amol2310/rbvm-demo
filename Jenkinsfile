pipeline {
  agent any 

  environment {
    APP_IMAGE = "vulhub/flask:1.1.1"
    SCANNER_IMAGE = "amarathe:latest"
  }

  stages {
    stage('Checkout Code') {
      steps {
        checkout scm
      }
    }

    stage('Build App Docker Image') {
      steps {
        sh '''
          docker pull vulhub/flask:1.1.1 #instead of building the image, pulling the vulnerabile image from vulhub.
        '''
      }
    }

    stage('Run RBVM Scanner') {
      steps {
        sh '''
          echo "Pulling scanner image..."
          #docker pull $SCANNER_IMAGE

          echo "Running RBVM scanner..."
          docker run --rm \
            -v \$WORKSPACE:/scanner/scanner_output \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -p 8501:8501 \
            $SCANNER_IMAGE $APP_IMAGE

          echo "RBVM scan completed."
        '''
      }
    }
    
    stage('Push Artifact (Placeholder)') {
      steps {
        echo '[Simulated] Pushing built image and reports to Nexus/Artifactory...'
        sleep 2
      }
    }

    stage('Notify Security Team (Placeholder)') {
      steps {
        echo '[Simulated] Sending Slack/email alert to security team...'
        sleep 2
      }
    }
  }

  post {
    success {
      echo 'RBVM scan completed successfully.'
      echo 'Visit http://localhost:8501 for dashboard.'
    }
    failure {
      echo 'Scan failed due to high-risk CVEs.'
      echo 'Visit http://localhost:8501 for dashboard.'
    }
  }
}
