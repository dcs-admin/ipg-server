## Deployment through Docker for Nest Server App on AWS EC2 Instance
## Author : Ram
## DevOps: Krishna

##--no-cache 
cd /home/ec2-user/srans/nest-server
git checkout -f $1
git pull 
docker build -f /home/ec2-user/srans/nest-server/Dockerfile -t nest-server .
docker stop nest-server-container
docker rm nest-server-container
docker run -p 8080:8080 -d -it -v /home/ec2-user/srans/nest-server/uploads:/app/uploads/ --name nest-server-container nest-server

## Validation to verify whether docker container loaded successfully
docker ps -a

docker logs nest-server-container --tail 100
