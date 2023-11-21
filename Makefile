SCALA_FILES := $(wildcard src/main/scala/*.scala)
CLASS_FILES := $(patsubst src/main/scala/%.scala, target/scala-2.12/classes/%.class, $(SCALA_FILES))

.PHONY: all clean

all: target/scala-2.12/myproject.jar

target/scala-2.12/myproject.jar: $(CLASS_FILES)
    scalac -d target/scala-2.12/classes $(SCALA_FILES)
    jar cf $@ -C target/scala-2.12/classes .

target/scala-2.12/classes/%.class: src/main/scala/%.scala
    mkdir -p $(dir $@)
    scalac -d target/scala-2.12/classes $<

clean:
    rm -rf target