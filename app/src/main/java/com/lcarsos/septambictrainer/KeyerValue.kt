package com.lcarsos.septambictrainer

/**
 * Indexing into KeyerValue is 0b0FNCIMRP
 * Center is bit position 5
 * Near is bit position 6
 * Far is bit position 7
 *
 * Clearly, a septambic keyer only needs 7 bits.
 */
enum class KeyerValue(val value: Int) {    // PRMI NCF
    Null(0x0),                       // 0000 000
    LowercaseW(0x01),                // 1000 000
    LowercaseY(0x02),                // 0100 000
    LowercaseU(0x03),                // 1100 000
    LowercaseR(0x04),                // 0010 000
    //Unused(0x05),                    // 1010 000
    LowercaseH(0x06),                // 0110 000
    LowercaseS(0x07),                // 1110 000
    LowercaseI(0x08),                // 0001 000
    LowercaseB(0x09),                // 1001 000
    LowercaseK(0x0a),                // 0101 000
    LowercaseZ(0x0b),                // 1101 000
    LowercaseD(0x0c),                // 0011 000
    //Unused(0x0d),                    // 1011 000
    LowercaseE(0x0e),                // 0111 000
    LowercaseT(0x0f),                // 1111 000

    Space(0x10),                     // 0000 010

    LowercaseF(0x11),                // 1000 010
    LowercaseG(0x12),                // 0100 010
    LowercaseV(0x13),                // 1100 010
    LowercaseC(0x14),                // 0010 010
    CloseBracket(0x15),              // 1010 010
    LowercaseP(0x16),                // 0110 010
    LowercaseN(0x17),                // 1110 010
    LowercaseL(0x18),                // 0001 010
    LowercaseX(0x19),                // 1001 010
    LowercaseJ(0x1a),                // 0101 010
    LowercaseQ(0x1b),                // 1101 010
    LowercaseM(0x1c),                // 0011 010
    OpenBracket(0x1d),               // 1011 010
    LowercaseA(0x1e),                // 0111 010
    LowercaseO(0x1f),                // 1111 010

    Shift(0x20),                     // 0000 100

    Function(0x21),                  // 1000 100
    Escape(0x22),                    // 0100 100
    Semicolon(0x23),                 // 1100 100
    Comma(0x24),                     // 0010 100
    //CloseBracket(0x25),              // 1010 100
    Period(0x26),                    // 0110 100
    Alt(0x27),                // 1110 100
    //LowercaseL(0x28),                // 0001 100
    Insert(0x29),                    // 1001 100
    //LowercaseJ(0x2a),                // 0101 100
    Control(0x2b),                // 1101 100
    //LowercaseM(0x2c),                // 0011 100
    //OpenBracket(0x2d),               // 1011 100
    SingleQuote(0x2e),               // 0111 100
    ShiftLock(0x2f),                 // 1111 100
}