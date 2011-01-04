package com.twitter.chainsaw

/**
 * Mix this into any class to automatically get a Logger
 */
trait Logging {
  protected val log = Logger()
}
