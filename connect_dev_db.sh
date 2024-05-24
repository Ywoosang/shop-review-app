#!/bin/bash

# .env 파일을 읽어 쉘 스크립트의 로컬 변수로 설정
while IFS='=' read -r key value; do
  if [[ ! $key =~ ^# && -n $key ]]; then
    eval "${key}='${value}'"
  fi
done < .env

CONTAINER_NAME="shop-review-db"

# 컨티이너가 실행중인지 확인
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
  echo "Container is running. Connecting to MySQL..."

  # MySQL에 접속
  mysql -h 127.0.0.1 -P 3307 -u"$DB_USERNAME" -p"$DB_PASSWORD" "$DB_NAME"
else
  echo "Container is not running. Exiting script."
fi
