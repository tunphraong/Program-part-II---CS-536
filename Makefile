###
# This Makefile can be used to make a scanner for the C-- language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP)
CP = ../deps:.

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: cdull.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) cdull.jlex.java

cdull.jlex.java: cdull.jlex sym.class
	java -cp $(CP) JLex.Main cdull.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test:
	diff test_int_literal.out test_int_literal_expect.out

	diff test_int_literal.out test_int_literal_expect.out
###
# clean up
###

clean:
	rm -f *~ *.class cdull.jlex.java

cleantest:
	rm -f allTokens.out
