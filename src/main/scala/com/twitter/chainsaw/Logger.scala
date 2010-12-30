package com.twitter.chainsaw

import org.slf4j

object Logger {
  /**
   * Return a logger for the class name of the class/object that called
   * this method. Normally you would use this in a "private val"
   * declaration on the class/object. The class name is determined
   * by sniffing around on the stack.
   */
  def apply(): Logger = get(2)

  /**
   * Returns a logger for the name of the specified class.
   */
  def apply(clz: Class[_]): Logger = {
    new Logger(slf4j.LoggerFactory.getLogger(clz))
  }

  /**
   * Returns a logger for the specified name
   */
  def apply(name: String): Logger = {
    new Logger(slf4j.LoggerFactory.getLogger(name))
  }

  /**
   * Returns a logger for the class of the specified object
   */
  def apply(obj: AnyRef): Logger = apply(obj.getClass())

  private def get(depth: Int): Logger = getForClassName(new Throwable().getStackTrace()(depth).getClassName)

  private def getForClassName(className: String) = {
    apply(
      if (className.endsWith("$")) className.substring(0, className.length - 1)
      else className
    )
  }
}

class Logger(val underlying: slf4j.Logger) {
  def isDebugEnabled = underlying.isDebugEnabled
  def isErrorEnabled = underlying.isErrorEnabled
  def isInfoEnabled = underlying.isInfoEnabled
  def isTraceEnabled = underlying.isTraceEnabled
  def isWarnEnabled = underlying.isWarnEnabled

  def trace(msg: String) = underlying.trace(msg)
  def trace(msg: String, items: Any*) = underlying.trace(msg, items.toArray)
  def trace(thrown: Throwable, msg: String, items: Any*) = underlying.trace(msg, thrown, items.toArray)

  def debug(msg: String) = underlying.debug(msg)
  def debug(msg: String, items: Any*) = underlying.debug(msg, items.toArray)
  def debug(thrown: Throwable, msg: String, items: Any*) = underlying.debug(msg, thrown, items.toArray)

  def info(msg: String) = underlying.info(msg)
  def info(msg: String, items: Any*) = underlying.info(msg, items.toArray)
  def info(thrown: Throwable, msg: String, items: Any*) = underlying.info(msg, thrown, items.toArray)

  def warn(msg: String) = underlying.warn(msg)
  def warn(msg: String, items: Any*) = underlying.warn(msg, items.toArray)
  def warn(thrown: Throwable, msg: String, items: Any*) = underlying.warn(msg, thrown, items.toArray)

  def error(msg: String) = underlying.error(msg)
  def error(msg: String, items: Any*) = underlying.error(msg, items.toArray)
  def error(thrown: Throwable, msg: String, items: Any*) = underlying.error(msg, thrown, items.toArray)

  def ifTrace(msg: => String) = if (isTraceEnabled) underlying.trace(msg)
  def ifTrace(thrown: Throwable, msg: => String) = if (isTraceEnabled) underlying.trace(msg, thrown)

  def ifDebug(msg: => String) = if (isDebugEnabled) underlying.debug(msg)
  def ifDebug(thrown: Throwable, msg: => String) = if (isDebugEnabled) underlying.debug(msg, thrown)

  def ifInfo(msg: => String) = if (isInfoEnabled) underlying.info(msg)
  def ifInfo(thrown: Throwable, msg: => String) = if (isInfoEnabled) underlying.info(msg, thrown)

  def ifWarn(msg: => String) = if (isWarnEnabled) underlying.warn(msg)
  def ifWarn(thrown: Throwable, msg: => String) = if (isWarnEnabled) underlying.warn(msg, thrown)

  def ifError(msg: => String) = if (isErrorEnabled) underlying.error(msg)
  def ifError(thrown: Throwable, msg: => String) = if (isErrorEnabled) underlying.error(msg, thrown)
}
