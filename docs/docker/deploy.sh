mvn -X clean package -Dmaven.test.skip=true -Pdev

docker-compose -p oneblog up -d

