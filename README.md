Scorecard
=========

[![Build Status](https://secure.travis-ci.org/dvberkel/ScoreCard.png)](http://travis-ci.org/dvberkel/ScoreCard)

This project will provide a scorecard for a certain type of card
games. See what we are working on via our
[Trello board](https://trello.com/board/scorecard/4f3a69e826d5cf580c05e727 "Trello board for ScoreCard project")

Card game
---------

The type of card game that is supported with the scorecard from this
project is described below

Four players play cards in rounds. In every round a number of cards is
dealt to each player. In the bidding phase players announce how many
tricks they will win.

After the bidding phase the cards are played. Suits and values are
respected as in regular card games. After all cards are played the
number of tricks won by each player are counted.

Every player is received a score for the round. If a player has the
same number of tricks as she announced in the bidding phase she is
rewarded with a bonus plus a trick value for each trick won. If a
player has miscalled the number of tricks she would win, she is
penalized for every trick over or under her estimate.