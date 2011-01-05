package com.twitter.chainsaw

import org.specs.mock.Mockito
import org.specs.Specification
import org.slf4j

class LoggerSpec extends Specification with Mockito {
  abstract class Level(val ord: Int) extends Ordered[Level] {
    def compare(that: Level) = this.ord compare that.ord
  }
  case object trace extends Level(5)
  case object debug extends Level(4)
  case object info extends Level(3)
  case object warn extends Level(2)
  case object error extends Level(1)
  case object fatal extends Level(0)

  "Logger" should {
    val underlying = mock[slf4j.Logger]
    val log = new Logger(underlying)
    val exception = new Exception

    def setLevel(level: Level) {
      underlying.isTraceEnabled returns level >= trace
      underlying.isDebugEnabled returns level >= debug
      underlying.isInfoEnabled returns level >= info
      underlying.isWarnEnabled returns level >= warn
      underlying.isErrorEnabled returns level >= error
    }

    "trace" in {
      setLevel(trace)

      "plain message" in {
        log.trace("hello")
        got { one(underlying).trace("hello") }
      }

      "formatted message" in {
        log.trace("hello %s", "jeremy")
        got { one(underlying).trace("hello jeremy") }
      }

      "with exception" in {
        log.trace(exception, "hello %s", "jeremy")
        got { one(underlying).trace("hello jeremy", exception) }
      }

      "no-op if level is higher" in {
        setLevel(debug)

        "formatted message" in {
          log.trace("hello %s", "jeremy")
          got { no(underlying).trace("hello jeremy") }
        }

        "with exception" in {
          log.trace(exception, "hello %s", "jeremy")
          got { no(underlying).trace("hello jeremy", exception) }
        }
      }
    }

    "debug" in {
      setLevel(debug)

      "plain message" in {
        log.debug("hello")
        got { one(underlying).debug("hello") }
      }

      "formatted message" in {
        log.debug("hello %s", "jeremy")
        got { one(underlying).debug("hello jeremy") }
      }

      "with exception" in {
        log.debug(exception, "hello %s", "jeremy")
        got { one(underlying).debug("hello jeremy", exception) }
      }

      "no-op if level is higher" in {
        setLevel(info)

        "formatted message" in {
          log.debug("hello %s", "jeremy")
          got { no(underlying).debug("hello jeremy") }
        }

        "with exception" in {
          log.debug(exception, "hello %s", "jeremy")
          got { no(underlying).debug("hello jeremy", exception) }
        }
      }
    }

    "info" in {
      setLevel(info)

      "plain message" in {
        log.info("hello")
        got { one(underlying).info("hello") }
      }

      "formatted message" in {
        log.info("hello %s", "jeremy")
        got { one(underlying).info("hello jeremy") }
      }

      "with exception" in {
        log.info(exception, "hello %s", "jeremy")
        got { one(underlying).info("hello jeremy", exception) }
      }

      "no-op if level is higher" in {
        setLevel(warn)

        "formatted message" in {
          log.info("hello %s", "jeremy")
          got { no(underlying).info("hello jeremy") }
        }

        "with exception" in {
          log.info(exception, "hello %s", "jeremy")
          got { no(underlying).info("hello jeremy", exception) }
        }
      }
    }

    "warn" in {
      setLevel(warn)

      "plain message" in {
        log.warn("hello")
        got { one(underlying).warn("hello") }
      }

      "formatted message" in {
        log.warn("hello %s", "jeremy")
        got { one(underlying).warn("hello jeremy") }
      }

      "with exception" in {
        log.warn(exception, "hello %s", "jeremy")
        got { one(underlying).warn("hello jeremy", exception) }
      }

      "no-op if level is higher" in {
        setLevel(error)

        "formatted message" in {
          log.warn("hello %s", "jeremy")
          got { no(underlying).warn("hello jeremy") }
        }

        "with exception" in {
          log.warn(exception, "hello %s", "jeremy")
          got { no(underlying).warn("hello jeremy", exception) }
        }
      }
    }

    "error" in {
      setLevel(error)

      "plain message" in {
        log.error("hello")
        got { one(underlying).error("hello") }
      }

      "formatted message" in {
        log.error("hello %s", "jeremy")
        got { one(underlying).error("hello jeremy") }
      }

      "with exception" in {
        log.error(exception, "hello %s", "jeremy")
        got { one(underlying).error("hello jeremy", exception) }
      }

      "no-op if level is higher" in {
        setLevel(fatal)

        "formatted message" in {
          log.error("hello %s", "jeremy")
          got { no(underlying).error("hello jeremy") }
        }

        "with exception" in {
          log.error(exception, "hello %s", "jeremy")
          got { no(underlying).error("hello jeremy", exception) }
        }
      }
    }
  }
}