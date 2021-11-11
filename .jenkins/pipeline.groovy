pipeline {
    agent any

    parameters{
        string(defaultValue: '5', name: 'THREADS')
        choice(choices: ['chrome', 'firefox'], name: 'BROWSER_NAME')
        choice(choices: ['master', 'api'], name: 'BRANCH_NAME')
        choice(choices: ['test', 'login'], name: 'TASK_NAME')
    }

    stages {
        stage('Source code') {
            steps {
                git branch: '*/${GIT_BRANCH}',
                        credentialsId: '0338b4f6-cbc3-4550-8e91-99976debed8d',
                        url: 'ssh://git@github.com:Oleg1717/allure-testops-tests-my.git'
            }
        }

        stage('Add property files') {
            steps {
                writeFile file: 'src/test/resources/config/remote.properties', text: '''browser.name=chrome
                    browser.version=91.0
                    browser.size=1920x1080
                    browser.mobile.view=
                    #browser.mobile.view=iPhone X
                    remote.driver.url=https://user1:1234@selenoid.autotests.cloud/wd/hub/
                    video.storage.url=https://selenoid.autotests.cloud/video/'''

                writeFile file: 'src/test/resources/config/app.properties', text: '''web.url=https://allure.autotests.cloud
                    api.base.uri=https://allure.autotests.cloud
                    api.uaa.path=/api/uaa/
                    api.rs.path=/api/rs/
                    project.id=308
                    user.login=qaguru6
                    user.password=qaguru
                    user2.login=testuser
                    user2.password=testuser12354
                    user.token=cf46557d-09e9-49f1-b4e0-4d47ff246f6d
                    xsrf.token=cf46557d-09e9-49f1-b4e0-4d47ff246f6d'''
            }
        }

        stage('Build') {
            steps {
                withAllureUpload(name: '${JOB_NAME} - #${BUILD_NUMBER}', projectId: '398', results: [[path: 'build/allure-results']], serverId: 'allure-server') {
                    sh '/home/testuser/.sdkman/candidates/gradle/6.8.3/bin/gradle clean ${TASK_NAME} -Dthreads=${THREADS} -Dbrowser=${BROWSER_NAME}'
                }
            }
        }
    }

    post {
        always {
            allure results: [[path: 'build/allure-results']]

            sh '''java  \\
				"-DprojectName=REG-FORM-TESTS" \\
				"-Denv=prod" \\
				"-DreportLink=${BUILD_URL}" \\
				"-Dconfig.file=/home/testuser/.allure-notifications/config/ov_autotests_bot.json" \\
				-jar /home/testuser/.allure-notifications/releases/allure-notifications-${ALLURE_NOTIFICATIONS_VERSION}.jar '''
        }
    }
}

