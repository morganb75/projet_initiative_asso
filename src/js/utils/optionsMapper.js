//conversion d'un objet type map(value ,label) en un liste [val1,val2,...]
const optionsMapper = (options) => {
    return options.map(item => item.value)
}
export default optionsMapper