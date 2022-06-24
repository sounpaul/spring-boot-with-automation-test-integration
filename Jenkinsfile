pipeline {
    agent any

    stages {
        stage('Serenity BDD Tests') {
            steps {
                echo "Start : Serenity BDD Tests"
                sh './gradlew clean bddTest -Dcucumber.filter.tags="@${Tag}" -Denvironment=${Region} gradle clean bddTest -Dcucumber.filter.tags="@AddEmployee" -Dusername=${USername} -Dpassword=${Password}'
                echo "End : Serenity BDD Tests"
            }
        }
        stage('Gatling Simulation') {
            steps {
                echo "Start : Gatling Simulation"
                sh './gradlew clean gatlingRun -Dduration=${Duration} -Dusers=${TPS} -Denvironment=${Region}'
                echo "End : Gatling Simulation"
            }
        }
    }
}