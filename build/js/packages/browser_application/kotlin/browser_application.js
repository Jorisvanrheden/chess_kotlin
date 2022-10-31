(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'browser_application'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'browser_application'.");
    }
    root.browser_application = factory(typeof browser_application === 'undefined' ? {} : browser_application, kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var Array_0 = Array;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  PlayerType.prototype = Object.create(Enum.prototype);
  PlayerType.prototype.constructor = PlayerType;
  Bishop.prototype = Object.create(Piece.prototype);
  Bishop.prototype.constructor = Bishop;
  King.prototype = Object.create(Piece.prototype);
  King.prototype.constructor = King;
  Knight.prototype = Object.create(Piece.prototype);
  Knight.prototype.constructor = Knight;
  Pawn.prototype = Object.create(Piece.prototype);
  Pawn.prototype.constructor = Pawn;
  Queen.prototype = Object.create(Piece.prototype);
  Queen.prototype.constructor = Queen;
  Rook.prototype = Object.create(Piece.prototype);
  Rook.prototype.constructor = Rook;
  function main() {
    console.log('Initializing module');
    testMethod();
    externalMethod();
    externalMethod2();
    var coord = new Coord(0, 0);
    var board = new Wrapper(10, 10);
    contains(0, 0);
  }
  function externalMethod2() {
    return 'Some external stuff';
  }
  function contains(x, y) {
    var otherBoard = new Board(10, 10);
    return otherBoard.isValidCoordinate_p7agz6$(new Coordinate(x, y));
  }
  function externalMethod() {
    return 'Some external stuff';
  }
  function testMethod() {
    return 'Testing value returned';
  }
  function Coord(x, y) {
    this.x = x;
    this.y = y;
  }
  Coord.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Coord',
    interfaces: []
  };
  function Wrapper(sizeX, sizeY) {
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.board_0 = new Board(this.sizeX, this.sizeY);
    this.getMoves(0, 0);
  }
  Wrapper.prototype.getMoves = function (x, y) {
    var tmp$, tmp$_0;
    var coordinate = new Coordinate(x, y);
    tmp$ = this.board_0.getPiece_p7agz6$(coordinate);
    if (tmp$ == null) {
      return [];
    }
    var piece = tmp$;
    var moves = piece.getMoves_1tncbh$(coordinate, this.board_0);
    var movePairs = ArrayList_init();
    tmp$_0 = moves.iterator();
    while (tmp$_0.hasNext()) {
      var m = tmp$_0.next();
      movePairs.add_11rb$(new Coord(m.x, m.y));
    }
    return copyToArray(movePairs);
  };
  Wrapper.prototype.getBoardRepresentation = function () {
    return this.board_0.getBoardRepresentation();
  };
  Wrapper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Wrapper',
    interfaces: []
  };
  function Board(sizeX, sizeY) {
    this.sizeX_0 = sizeX;
    this.sizeY_0 = sizeY;
    var array = Array_0(this.sizeX_0);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      var array_0 = Array_0(this.sizeY_0);
      var tmp$_0;
      tmp$_0 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
        array_0[i_0] = null;
      }
      array[i] = array_0;
    }
    this.matrix_0 = array;
    this.matrix_0[0][0] = new Rook(PlayerType$WHITE_getInstance());
    this.matrix_0[0][1] = new Knight(PlayerType$WHITE_getInstance());
    this.matrix_0[0][2] = new Bishop(PlayerType$WHITE_getInstance());
    this.matrix_0[0][3] = new Queen(PlayerType$WHITE_getInstance());
    this.matrix_0[0][4] = new King(PlayerType$WHITE_getInstance());
  }
  Board.prototype.getBoardRepresentation = function () {
    var tmp$, tmp$_0;
    var array = Array_0(this.sizeX_0);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_1; i++) {
      var array_0 = Array_0(this.sizeY_0);
      var tmp$_2;
      tmp$_2 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_2; i_0++) {
        array_0[i_0] = 0;
      }
      array[i] = array_0;
    }
    var map = array;
    tmp$ = this.sizeX_0;
    for (var i_1 = 0; i_1 < tmp$; i_1++) {
      tmp$_0 = this.sizeY_0;
      for (var j = 0; j < tmp$_0; j++) {
        var piece = this.matrix_0[i_1][j];
        if (piece != null) {
          var typeId = piece.getTypeId();
          console.log(typeId);
          if (piece.playerType === PlayerType$BLACK_getInstance())
            typeId = typeId + 6 | 0;
          map[i_1][j] = typeId;
        } else {
          map[i_1][j] = 0;
        }
      }
    }
    return map;
  };
  Board.prototype.isValidCoordinate_p7agz6$ = function (coordinate) {
    if (coordinate.x < 0 || coordinate.x >= this.sizeX_0)
      return false;
    if (coordinate.y < 0 || coordinate.y >= this.sizeY_0)
      return false;
    return true;
  };
  Board.prototype.getPiece_p7agz6$ = function (coordinate) {
    return this.matrix_0[coordinate.x][coordinate.y];
  };
  Board.prototype.containsPiece_p7agz6$ = function (coordinate) {
    return this.matrix_0[coordinate.x][coordinate.y] != null;
  };
  Board.prototype.isEqualPlayerType_icghkg$ = function (originCoordinate, targetCoordinate) {
    var originPiece = this.matrix_0[originCoordinate.x][originCoordinate.y];
    var targetPiece = this.matrix_0[targetCoordinate.x][targetCoordinate.y];
    if (originPiece != null && targetPiece != null) {
      return originPiece.playerType === targetPiece.playerType;
    }
    return false;
  };
  Board.prototype.isValidMove_icghkg$ = function (originCoordinate, targetCoordinate) {
    var tmp$;
    if (!this.isValidCoordinate_p7agz6$(targetCoordinate)) {
      return false;
    }
    if (this.containsPiece_p7agz6$(targetCoordinate)) {
      tmp$ = !this.isEqualPlayerType_icghkg$(originCoordinate, targetCoordinate);
    } else {
      tmp$ = true;
    }
    return tmp$;
  };
  Board.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Board',
    interfaces: []
  };
  function Coordinate(x, y) {
    this.x = x;
    this.y = y;
  }
  Coordinate.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Coordinate',
    interfaces: []
  };
  Coordinate.prototype.component1 = function () {
    return this.x;
  };
  Coordinate.prototype.component2 = function () {
    return this.y;
  };
  Coordinate.prototype.copy_vux9f0$ = function (x, y) {
    return new Coordinate(x === void 0 ? this.x : x, y === void 0 ? this.y : y);
  };
  Coordinate.prototype.toString = function () {
    return 'Coordinate(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + ')';
  };
  Coordinate.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    return result;
  };
  Coordinate.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y)))));
  };
  function Direction(x, y) {
    this.x = x;
    this.y = y;
  }
  Direction.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Direction',
    interfaces: []
  };
  Direction.prototype.component1 = function () {
    return this.x;
  };
  Direction.prototype.component2 = function () {
    return this.y;
  };
  Direction.prototype.copy_vux9f0$ = function (x, y) {
    return new Direction(x === void 0 ? this.x : x, y === void 0 ? this.y : y);
  };
  Direction.prototype.toString = function () {
    return 'Direction(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + ')';
  };
  Direction.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    return result;
  };
  Direction.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y)))));
  };
  function PlayerType(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function PlayerType_initFields() {
    PlayerType_initFields = function () {
    };
    PlayerType$WHITE_instance = new PlayerType('WHITE', 0);
    PlayerType$BLACK_instance = new PlayerType('BLACK', 1);
  }
  var PlayerType$WHITE_instance;
  function PlayerType$WHITE_getInstance() {
    PlayerType_initFields();
    return PlayerType$WHITE_instance;
  }
  var PlayerType$BLACK_instance;
  function PlayerType$BLACK_getInstance() {
    PlayerType_initFields();
    return PlayerType$BLACK_instance;
  }
  PlayerType.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PlayerType',
    interfaces: [Enum]
  };
  function PlayerType$values() {
    return [PlayerType$WHITE_getInstance(), PlayerType$BLACK_getInstance()];
  }
  PlayerType.values = PlayerType$values;
  function PlayerType$valueOf(name) {
    switch (name) {
      case 'WHITE':
        return PlayerType$WHITE_getInstance();
      case 'BLACK':
        return PlayerType$BLACK_getInstance();
      default:
        throwISE('No enum constant my.qualified.packagename.model.PlayerType.' + name);
    }
  }
  PlayerType.valueOf_61zpoe$ = PlayerType$valueOf;
  function getMovesInDirection(direction, coordinate, board) {
    var coordinates = ArrayList_init();
    var activeCoordinate = coordinate;
    while (true) {
      activeCoordinate = new Coordinate(activeCoordinate.x + direction.x | 0, activeCoordinate.y + direction.y | 0);
      if (!board.isValidCoordinate_p7agz6$(activeCoordinate)) {
        break;
      }
      if (board.containsPiece_p7agz6$(activeCoordinate)) {
        if (!board.isEqualPlayerType_icghkg$(coordinate, activeCoordinate)) {
          coordinates.add_11rb$(activeCoordinate);
        }
        break;
      } else {
        coordinates.add_11rb$(activeCoordinate);
      }
    }
    return coordinates;
  }
  function Bishop(playerType) {
    Piece.call(this, playerType);
  }
  Bishop.prototype.getMoves_1tncbh$ = function (coordinate, board) {
    var tmp$;
    var coordinates = ArrayList_init();
    var directions = listOf([new Direction(1, 1), new Direction(-1, -1), new Direction(-1, 1), new Direction(1, -1)]);
    tmp$ = directions.iterator();
    while (tmp$.hasNext()) {
      var direction = tmp$.next();
      coordinates.addAll_brywnq$(getMovesInDirection(direction, coordinate, board));
    }
    return coordinates;
  };
  Bishop.prototype.getTypeId = function () {
    return 3;
  };
  Bishop.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Bishop',
    interfaces: [Piece]
  };
  function King(playerType) {
    Piece.call(this, playerType);
  }
  King.prototype.getMoves_1tncbh$ = function (coordinate, board) {
    var tmp$;
    var coordinates = ArrayList_init();
    var directions = listOf([new Direction(1, 0), new Direction(-1, 0), new Direction(0, 1), new Direction(0, -1), new Direction(1, 1), new Direction(-1, -1), new Direction(-1, 1), new Direction(1, -1)]);
    tmp$ = directions.iterator();
    while (tmp$.hasNext()) {
      var direction = tmp$.next();
      var targetCoordinate = new Coordinate(coordinate.x + direction.x | 0, coordinate.y + direction.y | 0);
      if (board.isValidMove_icghkg$(coordinate, targetCoordinate)) {
        coordinates.add_11rb$(targetCoordinate);
      }
    }
    return coordinates;
  };
  King.prototype.getTypeId = function () {
    return 1;
  };
  King.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'King',
    interfaces: [Piece]
  };
  function Knight(playerType) {
    Piece.call(this, playerType);
  }
  Knight.prototype.getMoves_1tncbh$ = function (coordinate, board) {
    var tmp$;
    var coordinates = ArrayList_init();
    var targetCoordinates = listOf([new Coordinate(coordinate.x - 1 | 0, coordinate.y + 2 | 0), new Coordinate(coordinate.x + 1 | 0, coordinate.y + 2 | 0), new Coordinate(coordinate.x - 1 | 0, coordinate.y - 2 | 0), new Coordinate(coordinate.x + 1 | 0, coordinate.y - 2 | 0), new Coordinate(coordinate.x - 2 | 0, coordinate.y + 1 | 0), new Coordinate(coordinate.x - 2 | 0, coordinate.y - 1 | 0), new Coordinate(coordinate.x + 2 | 0, coordinate.y + 1 | 0), new Coordinate(coordinate.x + 2 | 0, coordinate.y - 1 | 0)]);
    tmp$ = targetCoordinates.iterator();
    while (tmp$.hasNext()) {
      var targetCoordinate = tmp$.next();
      if (board.isValidMove_icghkg$(coordinate, targetCoordinate)) {
        coordinates.add_11rb$(targetCoordinate);
      }
    }
    return coordinates;
  };
  Knight.prototype.getTypeId = function () {
    return 4;
  };
  Knight.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Knight',
    interfaces: [Piece]
  };
  function Pawn(playerType) {
    Piece.call(this, playerType);
  }
  Pawn.prototype.getMoves_1tncbh$ = function (coordinate, board) {
    return emptyList();
  };
  Pawn.prototype.getTypeId = function () {
    return 6;
  };
  Pawn.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Pawn',
    interfaces: [Piece]
  };
  function Piece(playerType) {
    this.playerType = playerType;
  }
  Piece.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Piece',
    interfaces: []
  };
  function Queen(playerType) {
    Piece.call(this, playerType);
  }
  Queen.prototype.getMoves_1tncbh$ = function (coordinate, board) {
    var tmp$;
    var coordinates = ArrayList_init();
    var directions = listOf([new Direction(1, 0), new Direction(-1, 0), new Direction(0, 1), new Direction(0, -1), new Direction(1, 1), new Direction(-1, -1), new Direction(-1, 1), new Direction(1, -1)]);
    tmp$ = directions.iterator();
    while (tmp$.hasNext()) {
      var direction = tmp$.next();
      coordinates.addAll_brywnq$(getMovesInDirection(direction, coordinate, board));
    }
    return coordinates;
  };
  Queen.prototype.getTypeId = function () {
    return 2;
  };
  Queen.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Queen',
    interfaces: [Piece]
  };
  function Rook(playerType) {
    Piece.call(this, playerType);
  }
  Rook.prototype.getMoves_1tncbh$ = function (coordinate, board) {
    var tmp$;
    var coordinates = ArrayList_init();
    var directions = listOf([new Direction(1, 0), new Direction(-1, 0), new Direction(0, 1), new Direction(0, -1)]);
    tmp$ = directions.iterator();
    while (tmp$.hasNext()) {
      var direction = tmp$.next();
      coordinates.addAll_brywnq$(getMovesInDirection(direction, coordinate, board));
    }
    return coordinates;
  };
  Rook.prototype.getTypeId = function () {
    return 5;
  };
  Rook.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Rook',
    interfaces: [Piece]
  };
  var package$my = _.my || (_.my = {});
  var package$qualified = package$my.qualified || (package$my.qualified = {});
  var package$packagename = package$qualified.packagename || (package$qualified.packagename = {});
  package$packagename.main = main;
  package$packagename.externalMethod2 = externalMethod2;
  package$packagename.contains = contains;
  package$packagename.externalMethod = externalMethod;
  package$packagename.testMethod = testMethod;
  package$packagename.Coord = Coord;
  package$packagename.Wrapper = Wrapper;
  var package$logic = package$packagename.logic || (package$packagename.logic = {});
  package$logic.Board = Board;
  var package$model = package$packagename.model || (package$packagename.model = {});
  package$model.Coordinate = Coordinate;
  package$model.Direction = Direction;
  Object.defineProperty(PlayerType, 'WHITE', {
    get: PlayerType$WHITE_getInstance
  });
  Object.defineProperty(PlayerType, 'BLACK', {
    get: PlayerType$BLACK_getInstance
  });
  package$model.PlayerType = PlayerType;
  var package$moves = package$packagename.moves || (package$packagename.moves = {});
  package$moves.getMovesInDirection_kshtq2$ = getMovesInDirection;
  var package$pieces = package$packagename.pieces || (package$packagename.pieces = {});
  package$pieces.Bishop = Bishop;
  package$pieces.King = King;
  package$pieces.Knight = Knight;
  package$pieces.Pawn = Pawn;
  package$pieces.Piece = Piece;
  package$pieces.Queen = Queen;
  package$pieces.Rook = Rook;
  main();
  Kotlin.defineModule('browser_application', _);
  return _;
}));

//# sourceMappingURL=browser_application.js.map
