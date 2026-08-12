# CQRS - Read Service (도서 조회 시스템)

CQRS(Command Query Responsibility Segregation) 패턴을 적용한 도서 관리 시스템의 Read(조회) 서비스
데이터의 쓰기와 조회를 분리하여 조회 성능을 극대화하고, 이벤트 기반(Event-Driven) 아키텍처를 통해 두 시스템 간의 결합도를 낮추는 것을 목표로 설계


## System Architecture

* 데이터베이스 분리: 조회 성능 최적화를 위해 Read 전용 NoSQL(MongoDB) 데이터베이스를 사용
* 이벤트 기반 동기화: Write 서비스(MySQL)에서 발생한 데이터 변경 이벤트는 Apache Kafka를 통해 비동기적으로 전달되며, Read 서비스가 이를 Consume하여 MongoDB를 최신 상태로 동기화
* 독립적 확장 (Scalability): 트래픽이 집중되는 Read 서비스만 단독으로 스케일 아웃(Scale-out)할 수 있는 클라우드 네이티브 환경에 적합한 구조

## Tech Stack

* Language: Java 21
* Framework: Spring Boot
* Database: MongoDB (Spring Data MongoDB)
* Message Broker: Apache Kafka
* Build Tool: Gradle

## Key Features

1. Kafka Event Consumer
    * 토픽(book-create-topic)을 구독하여 Write 서비스에서 발행된 도서 생성 이벤트를 비동기적으로 수신
2. MongoDB Data Synchronization
    * 수신된 이벤트를 Document 형태로 MongoDB에 저장하여 조회 전용 데이터 파이프라인을 구축
3. High-Performance API
    * RDBMS의 복잡한 조인이나 트랜잭션 없이, MongoDB에서 완성된 Document를 즉시 반환하여 응답 속도(Latency)를 최소화


## Prerequisites
이 프로젝트를 실행하기 위해 로컬 환경에 아래의 서비스 필요
* MongoDB: localhost:27017
* Apache Kafka & Zookeeper: localhost:9092

