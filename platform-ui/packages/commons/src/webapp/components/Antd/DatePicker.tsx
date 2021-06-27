import dateFnsGenerateConfig from 'rc-picker/es/generate/dateFns';
import generatePicker from 'antd/es/date-picker/generatePicker';
import 'antd/es/date-picker/style/index.css';
const DatePicker = generatePicker<Date>(dateFnsGenerateConfig);
export default DatePicker;
