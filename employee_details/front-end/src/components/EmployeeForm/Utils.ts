// export const capitaliseEachWord = (str:string) =>{
//     return str.split('_').map((word)=>word.charAt(0).toUpperCase()+word.substring(1).
// toLowerCase()).join(' ');

// };
export const capitaliseEachWord = (str:string) =>{
    return str.split('_').map((word)=>word.charAt(0).toUpperCase()+word.substring(1).toLowerCase()).join(' ');
}

