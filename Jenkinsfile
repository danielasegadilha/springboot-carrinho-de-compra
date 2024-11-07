pipeline {
    agent any

    tools {
        maven 'Maven 3.6.3'  // Defina a versão do Maven configurada no Jenkins
        jdk 'OpenJDK 17'     // Defina a versão do JDK que você está utilizando
    }

    stages {
        stage('Verificar Repositório') {
            steps {
                // Clona o repositório GitHub usando configuração explícita
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], useRemoteConfigs: [[url: 'https://github.com/danielasegadilha/springboot-carrinho-de-compra.git']]
                ])
            }
        }

        stage('Compilar e Testar') {
            steps {
                // Executa o Maven para compilar o código e rodar os testes
                sh 'mvn clean install -DskipTests=false'
            }
        }

        stage('Gerar Artefato') {
            steps {
                // Gera o artefato .jar
                sh 'mvn package'
            }
        }

        stage('Verificar Artefato') {
            steps {
                script {
                    // Verifica se o artefato .jar foi gerado corretamente
                    def artifact = 'target/springboot-carrinho-de-compra-*.jar'  // Ajuste para o nome real do seu artefato
                    if (!fileExists(artifact)) {
                        error "O artefato .jar não foi gerado corretamente!"
                    }
                }
            }
        }

        stage('Construir Imagem Docker') {
            steps {
                script {
                    // Nome da imagem Docker com a tag do Build ID
                    def imageName = "springboot-carrinho-de-compra:${env.BUILD_ID}"

                    // Construa a imagem Docker com base no Dockerfile
                    sh "docker build -t ${imageName} ."
                }
            }
        }

        stage('Fazer Deploy em Docker') {
            steps {
                script {
                    // Nome da imagem Docker gerada
                    def imageName = "springboot-carrinho-de-compra:${env.BUILD_ID}"

                    // Parar e remover o container anterior, se existir
                    sh "docker stop springboot-carrinho-de-compra || true"
                    sh "docker rm springboot-carrinho-de-compra || true"

                    // Rodar o novo container com a imagem Docker criada
                    sh "docker run -d --name springboot-carrinho-de-compra -p 8080:8080 ${imageName}"
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
