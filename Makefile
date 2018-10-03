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
	java -cp $(CP) P2 0
	diff allTokens.out allTokens_expect.out
	diff test_int_literal.out test_int_literal_expect.out
	diff test_id.out test_id_expect.out
	diff test_string_lit.out test_string_lit_expect.out
	diff test_line.out test_line_expect.out

test_token:
	java -cp $(CP) P2 1
	diff test_id.out test_id_expect.out
test_id:
	java -cp $(CP) P2 2 
	diff test_id.out test_id_expect.out

test_string:
	java -cp $(CP) P2 3
	diff test_string_lit.out test_string_lit_expect.out

test_integer:
	java -cp $(CP) P2 4
	diff test_int_literal.out test_int_literal_expect.out

test_line:
	java -cp $(CP) P2 5
	diff test_line.out test_line_expect.out


###
# clean up
###

clean:
	rm -f *~ *.class cdull.jlex.java

cleantest:
	rm -f allTokens.out
