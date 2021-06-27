import dateFnsGenerateConfig from 'rc-picker/es/generate/dateFns';
import generateCalendar from 'antd/es/calendar/generateCalendar';
import 'antd/es/calendar/style/index.css';
const Calendar = generateCalendar<Date>(dateFnsGenerateConfig);
export default Calendar;
