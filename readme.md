# Account Service
1) Create an empty folder on your file system.
2) Clone or download this git repo
```

```
3) To run this application in docker do next instructions.
* Go to project root directory
* Find docker-compose-file
* Past your IP address in DB_URL parameter
* Check ports of containers. They should be opened on your system.
* Run next command:
   ```
   docker-compose up -d --build
   ```

This command will create two docker containers:
1) Postgres:11
2) Java:19
   Postgres will create a folder outside of project folder.
   You can open your browser and go by next link:
```
http://localhost:2541/swagger-ui/index.html
```
* Do first http request and create client. After creating the client, the client will be returned with a list of his accounts; you need to take the ID of the created account to carry out operations. CURL example
```
curl -X 'POST' \
  'http://localhost:2541/api/clients' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "documentId": "string",
  "documentType": "PASSPORT",
  "id": 0,
  "name": "string"
}'
```
* Do second http request and create Operation using copied account id. CURL example
```
curl -X 'POST' \
  'http://localhost:2541/api/operations' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "account": {
    "id": 101
  },
  "amount": 0.01,
  "id": 0,
  "type": "INCOME"
}'
```