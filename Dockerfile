FROM rabbitmq:3.12.9-management

RUN rabbitmq-plugins enable --offline rabbitmq_stomp

EXPOSE 15671 15672 61613