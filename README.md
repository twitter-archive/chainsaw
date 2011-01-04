# Chainsaw

Chainsaw is a thin Scala wrapper on top of SLF4J.  It provides support for var-args and for 
lazy args, and for easily creating Logger instances.  

Example:

  val log = Logger()
  ...
  log.info("blah blah")
  log.warn(exception, "go boom")
  
  