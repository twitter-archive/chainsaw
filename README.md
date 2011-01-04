# Chainsaw

Chainsaw is a thin Scala wrapper on top of SLF4J.  It provides support for var-args and for 
lazy args, and for easily creating Logger instances.  

You can create a Logger either with the Logger object factory methods, or you can mix the
Logging trait into your class and automatically get a Logger initialized with the contain 
class's name.
