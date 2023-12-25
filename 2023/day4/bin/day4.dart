import 'dart:math';

import 'package:day4/day4.dart' as day4;

import 'dart:io';
import 'dart:convert';
import 'dart:async';

void main() async {
  num calcPoints(int points) {
    if (points == 0) return 0;

    return pow(2, points - 1);
  }

  final file = File('input.txt');
  Stream<String> lines =
      file.openRead().transform(utf8.decoder).transform(LineSplitter());
  int points = 0;
  num sum = 0;
  int cardsSum = 0;

  Map<int, int> plusCards = {};

  try {
    await for (var line in lines) {
      final split = line.split(': ');

      final gameId = int.parse(split[0].split(' ').last);

      final gameSplit = split[1].split(' | ');
      final winning = gameSplit[0].split(' ');
      final elf = gameSplit[1].split(' ');

      for (var win in winning) {
        if (win.trim().isEmpty) continue;

        if (elf.contains(win)) {
          points++;
        }
      }
      final calc = calcPoints(points);
      sum += calc;

      final totalCards = plusCards[gameId] ?? 1;
      final totalPoints = points * totalCards;

      print('GameId -> $gameId NoC -> $totalCards');

      for (var i = (gameId + 1); i <= (gameId + points); i++) {
        plusCards[i] = (plusCards[i] ?? 1) + (totalCards);
      }

      cardsSum += totalCards;
      points = 0;
    }
  } catch (e) {
    print('Error: $e');
  }
  print('Total -> $sum Cards Sum -> $cardsSum');
}
