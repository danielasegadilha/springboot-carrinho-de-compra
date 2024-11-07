pipeline {
    agent any

    environment {
        // Definindo as ferramentas Maven e JDK no Jenkins
        MAVEN_HOME = tool name: 'Maven 3.8.1', type: 'ToolLocation'
        JAVA_HOME = tool name: 'JDK 17', type: 'ToolLocation'  // JDK 17, conforme mencionado anteriormente
    }

    stages {
        stage('Verificar Repositório') {
            steps {
                // Fazendo o checkout do repositório Git
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], useRemoteConfigs:[[url: 'https://github.com/danielasegadilha/springboot-carrinho-de-compra.git']]])
            }
        }

        stage('Construir o Projeto (Maven)') {
            steps {
                script {
                    // Realizando o build do projeto com Maven, sem testes
                    bat "\"${MAVEN_HOME}\\bin\\mvn\" clean package -DskipTests"
                }
            }
        }

        stage('Construir Imagem Docker') {
            steps {
                script {
                    def appName = 'springboot-carrinho-de-compra'
                    def imageTag = "${appName}:${env.BUILD_ID}"

                    // Construir a imagem Docker
                    bat "docker build -t ${imageTag} ."
                }
            }
        }

        stage('Fazer Deploy Docker') {
            steps {
                script {
                    def appName = 'springboot-carrinho-de-compra'
                    def imageTag = "${appName}:${env.BUILD_ID}"

                    // Parar e remover o container existente, se houver
                    bat "docker stop ${appName} || exit 0"
                    bat "docker rm ${appName} || exit 0"

                    // Executar o novo container
                    bat "docker run -d --name ${appName} -p 8080:8080 ${imageTag}"
                }
            }
        }
    }

    post {
        success {
            echo 'Deploy realizado com sucesso!'
        }
        failure {
            echo 'Houve um erro durante o deploy.'
        }
    }
}
