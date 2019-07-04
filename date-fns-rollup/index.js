import 'core-js/modules/es.array.find-index';
import {format, parse} from 'date-fns/esm'

const DateFns = {format, parse};
window['DateFns'] = DateFns;

export default DateFns;
