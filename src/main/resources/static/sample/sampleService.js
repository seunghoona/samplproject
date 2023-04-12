export const sampleService = {
	getSample : () => {
		return new Promise((resolve, reject) => {
			resolve([{"className" : "hello", "title" : "안녕하세요1"}, {"className" : "hello", "title" : "안녕하세요2"}, {"className" : "hello", "title" : "안녕하세요3"}])
		})
	}
}