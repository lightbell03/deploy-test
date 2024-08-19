let getStorageUnitText = (function() {
    let units  = ['B', 'KB', 'MB', 'GB', 'TB', 'PB'];
    let unitIndexes = {};
    units.forEach((e,i)=>unitIndexes[e]=i);
    let getUnit = function(size, baseUnit) {return units[~~(Math.log(size*Math.pow(1024, unitIndexes[baseUnit])) / Math.log(1024))];};
    return function(size, baseUnit='B', resultUnit) {
        resultUnit = resultUnit ?? getUnit(size, baseUnit);
        return `${(size / Math.pow(1024, unitIndexes[resultUnit]-unitIndexes[baseUnit])).toFixed(2)}${resultUnit}`;
    }
})();

console.log('= 자동 (기본 단위 Byte, 변환 단위 자동) =====================');
console.log(getStorageUnitText(1023));
console.log(getStorageUnitText(1024*1023));
console.log(getStorageUnitText(1024*1024*1023));
console.log(getStorageUnitText(1024*1024*1024*1023));
console.log(getStorageUnitText(1024*1024*1024*1024*1023));
console.log('= 기본 단위 KB, 변환 단위 자동 =====================');
console.log(getStorageUnitText(1023));
console.log(getStorageUnitText(1024*1023, 'KB'));
console.log(getStorageUnitText(1024*1024*1023, 'KB'));
console.log(getStorageUnitText(1024*1024*1024*1023, 'KB'));
console.log(getStorageUnitText(1024*1024*1024*1024*1023, 'KB'));
console.log('= 기본 단위 KB 및 변환 단위 TB =====================');
console.log(getStorageUnitText(1024*1023, 'KB', 'TB'));
console.log(getStorageUnitText(1024*1024*1023, 'KB', 'TB'));
console.log(getStorageUnitText(1024*1024*1024*1023, 'KB', 'TB'));
console.log(getStorageUnitText(1024*1024*1024*1024*1023, 'KB', 'TB'));
