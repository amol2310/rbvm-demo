pipeline {
  agent any 

  environment {
    APP_IMAGE = "vulhub/flask:1.1.1"
    //APP_IMAGE = "oraclelinux:9-slim"
    SCANNER_IMAGE = "maratheamol2310/rbvm:1.0.5"
    SCANNER_DASHBOARD = "maratheamol2310/rbvm-dashboard:1.0.0"
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
          docker pull $APP_IMAGE #instead of building the image, pulling the vulnerabile image from vulhub.
        '''
      }
    }

    stage('Run RBVM Scanner') {
      steps {
        sh '''
          set +x
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
            -v \$WORKSPACE/target:/app/scanner_output/target \
            --name rbvm-dashboard \
            $SCANNER_DASHBOARD

            ACT_ASAP_COUNT=$(jq '[.[] | select(.decision=="Act ASAP")] | length' \$WORKSPACE/target/prioritized_cves.json)
            ACT_COUNT=$(jq '[.[] | select(.decision=="Act")] | length' \$WORKSPACE/target/prioritized_cves.json)
            
            echo "CVE Prioritization complete."
            echo "Act ASAP CVEs: $ACT_ASAP_COUNT"
            echo "Act CVEs: $ACT_COUNT"
            
            # Fail build if any Act ASAP or Act CVEs exist
            if [ "$ACT_ASAP_COUNT" -gt 0 ] || [ "$ACT_COUNT" -gt 0 ]; then
              echo "High-risk vulnerabilities detected (Act ASAP or Act). Failing build..."
              exit 1
            else
              echo "No high-risk vulnerabilities. Build may proceed."
              exit 0
            fi
          
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
