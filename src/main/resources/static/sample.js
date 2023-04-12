export const validator = {
	setValidMessage: command => `${command} 는 올바른 명령입니다.`,
	setInvalidMessage: command => `${command} 는 올바른 명령이 아닙니다.`,
}

export const sample = {
	getHeader: header => `
  <div id= "${header.id}">
    <h1>${header.title}</h1>
  </div>
  `
}

export const test = {
	setValidMessage: command => `${command} 는 올바른 명령입니다.`,
	setInvalidMessage: command => `${command} 는 올바른 명령이 아닙니다.`,
}