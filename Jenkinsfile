pipeline {
  agent any 

  environment {
    APP_IMAGE = "vulhub/flask:1.1.1"
    SCANNER_IMAGE = "maratheamol2310/rbvm:1.0.4"
    SCANNER_DASHBOARD = "maratheamol2310/rbvm:2.0.0"
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
          docker pull $SCANNER_IMAGE

          echo "Running RBVM scanner..."
          docker run --rm \
           -v \$WORKSPACE:/scanner/scanner_output \
           -p 8501:8501 \
           $SCANNER_IMAGE $APP_IMAGE

           echo "Launching dashboard at localhost:8501"
           docker run -d \
            -p 8501:8501 \
            -v \$WORKSPACE/scanner_output:/app/scanner_output \
            --name rbvm-dashboard \
            $SCANNER_DASHBOARD


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
