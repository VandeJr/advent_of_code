const file = Bun.file('input.txt')
const buf = await file.arrayBuffer()
const bytes = new Uint8Array(buf);

let process = false
let number = 0
let sum = 0

const lineNumber = 141

let gearSum = 0
const gears: Map<number, number> = new Map()

for (let i = 0; i < bytes.length; i++) {
  const byte = bytes[i]
  // 48 -> 0 ASCII 57 -> 9 ASCII
  if (byte >= 48 && byte <= 57) {
    number = (number * 10) + (byte - 48)
    process = true
  } else {
    if (process) {
      process = false

      const numberLenght = number.toString().length

      const top = i - (numberLenght + 1 + lineNumber)
      const bottom = (i + lineNumber) - (numberLenght + 1)

      const analyze: number[] = [i, i - (numberLenght + 1)]
      for (let j = 0; j < (numberLenght + 2); j++) {
        analyze.push(top + j)
        analyze.push(bottom + j)
      }
      
      for(let j = 0; j < analyze.length; j++) {
        const lyzerInt = analyze[j]
        const lyzer = bytes[lyzerInt]
        // 46 -> . ASCII 10 -> \n ASCII
        if (lyzer == undefined || lyzer == 46 || lyzer == 10)
          continue
       
        // 42 -> * ASCII
        if (lyzer == 42) {
          if (gears.has(lyzerInt)) {
            gearSum += gears.get(lyzerInt)! * number
          } else {
            gears.set(lyzerInt, number)
          }
        }

        sum += number
      }

      number = 0
    }
  }
}

console.log(`Sum -> ${sum} Gears -> ${gearSum}`)
