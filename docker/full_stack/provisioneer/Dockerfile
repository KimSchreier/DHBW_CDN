FROM debian:stable

RUN apt-get update; \
  apt-get install -y python curl

RUN bash -c "python <(curl https://bootstrap.pypa.io/get-pip.py)"; \
  pip install -Iv cqlsh==4.0.1

COPY database.cql /
COPY ./awesome.sh /

RUN chmod 766 /awesome.sh

ENTRYPOINT ["./awesome.sh"]


