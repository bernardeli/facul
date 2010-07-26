igual([],[]).
igual(X,X).
igual([X,Xs], [Y,Ys]) :- igual(Xs,Ys).

prefix([],[]).
prefix([],X).
prefix([X|Xs],[Y|Ys]) :- igual(X,Y), prefix(Xs,Ys).

prefixo([],_).
prefixo([X|Xs],[Y|Ys]) :- prefixo(Xs,Ys)

concat(X,[],X).
concat([],Y,Y).
concat([X|Xs], Y, [X|Ys]) :- concat(Xs, Y, Ys).

reverse([],[]).
reverse([X|Xs],Ys) :- reverse(Xs, Yss), concat(Yss, [X], Ys]).

avo(X,Z) :- pai(X,Y), pai(Y,Z).
avo(X,Z) :- pai(X,Y), mae(Y,Z).
avo(X,Z) :- mae(X,Y), pai(Y,Z).
avo(X,Z) :- mae(X,Y), mae(Y,Z).
mae(marta, avi).
mae(marta, paulo).
mae(maria, silvia).
pai(jose, avi).
pai(jose, paulo).
pai(joao, marcos).
mae(sandra, jose).
mae(sandra, joao).
pai(pedro, jose).
pai(pedro, joao).
irmao(X,Z) :- pai(Y,X), pai(Y,Z), mae(K,X), mae(K,Z).

primo(X,Y) :- pai(Z,X), pai(P,Y), irmao(Z,P).
