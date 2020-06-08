# blockChain_model

На данный момент присутствует параллельная генерация блоков с валидацией блокчеина. Хеширование SHA-256. Можно сериализовать.
С каждым сгенерированным блоком возрастает сложность генерации хеша (увеличение необходимого кол-ва нулей в начале).
Пример:

Block:
Created by miner # 1
Id: 1
Timestamp: 1591638148066
Magic number: 1350191469
Hash of the previous block:
0
Hash of the block:
9f81587c4a9c7f4cf931512b64bcd755a01e9c2e217fa80b5088fcc10fc9eef1
Block was generating for 29 seconds
N was increased to 1

Block:
Created by miner # 25
Id: 2
Timestamp: 1591638148132
Magic number: -353797880
Hash of the previous block:
9f81587c4a9c7f4cf931512b64bcd755a01e9c2e217fa80b5088fcc10fc9eef1
Hash of the block:
03f249c8efd65f417bab9b9bf490d3c99d5b9ac0f301856eb71f09d1f355daed
Block was generating for 0 seconds
N was increased to 2

