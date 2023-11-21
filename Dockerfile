FROM ubuntu:latest

WORKDIR /game
COPY . .

RUN apt-get update && apt-get install -y curl make

RUN if [ $(uname -m) = "x86_64" ]; then \
    curl -fL https://github.com/coursier/coursier/releases/latest/download/cs-x86_64-pc-linux.gz | gzip -d > cs && chmod +x cs && ./cs setup -y; \
else \
    curl -fL https://github.com/VirtusLab/coursier-m1/releases/latest/download/cs-aarch64-pc-linux.gz | gzip -d > cs && chmod +x cs && ./cs setup -y; \
fi

RUN rm ./cs