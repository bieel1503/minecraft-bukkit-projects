package br.bieel.utils;

public class MillisHelper {
    private final long millis;

    public MillisHelper(long millis){
        this.millis = millis;
    }

    public int getYears(){
        return (int)(this.millis/TimeHelper.YEARS.getMultiplier());
    }
    public int getMonths(){
        return (int)(this.millis/TimeHelper.MONTHS.getMultiplier());
    }
    public int getWeeks(){
        return (int)(this.millis/TimeHelper.WEEKS.getMultiplier());
    }
    public int getDays(){
        return (int)(this.millis/TimeHelper.DAYS.getMultiplier());
    }
    public int getHours(){
        return (int)(this.millis/TimeHelper.HOURS.getMultiplier());
    }
    public int getMinutes(){
        return (int)(this.millis/TimeHelper.MINUTES.getMultiplier());
    }
    public int getSeconds(){
        return (int)(this.millis/TimeHelper.SECONDS.getMultiplier());
    }
    public long getMillis(){
        return this.millis;
    }
    public String getMessage(){
        int years = getYears();
        int months = getMonths();
        int weeks = getWeeks();
        int days = getDays();
        int hours = getHours();
        int minutes = getMinutes();
        int remainingDays = days-(months*31);
        int remainingWeeks = remainingDays/7;
        int remainingHours = hours-(days*24);
        int remainingMinutes = minutes%60;
        double seconds = ((double)this.millis/TimeHelper.SECONDS.getMultiplier())%60;
        String secondMessage = (seconds > .9D || seconds == 0 || seconds <-.9D ? "" + (int)seconds : String.format("%.1f", seconds));
        if(years > 0){
            int remainingMonths = months%12;
            remainingDays = remainingDays%7;
            remainingHours = remainingHours%24;
            if(remainingMonths > 0){
                if(remainingWeeks > 0){
                    if(remainingDays > 0){
                        return years + (years > 1 ? " anos, " : " ano, ") + remainingMonths + (remainingMonths > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas" : " semana") + " e " + remainingDays + (remainingDays > 1 ? " dias." : " dia.");
                    }
                    return years + (years > 1 ? " anos, " : " ano, ") + remainingMonths + (remainingMonths > 1 ? " meses, " : " mês, ") + " e " + remainingWeeks + (remainingWeeks > 1 ? " semanas." : " semana.");
                }else if(remainingDays > 0){
                    return years + (years > 1 ? " anos, " : " ano, ") + remainingMonths + (remainingMonths > 1 ? " meses" : " mês") + " e " + remainingDays + (remainingDays > 1 ? " dias." : " dia.");
                }
                return years + (years > 1 ? " anos" : " ano") + " e " + remainingMonths + (remainingMonths > 1 ? " meses." : " mês.");
            }else if(remainingWeeks > 0){
                if(remainingDays > 0){
                    return years + (years > 1 ? " anos, " : " ano, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas" : " semana") + " e " + remainingDays + (remainingDays > 1 ? " dias." : " dia.");
                }
                return years + (years > 1 ? " anos" : " ano") + " e " + remainingWeeks + (remainingWeeks > 1 ? " semanas." : " semana.");
            }else if(remainingDays > 0){
                return years + (years > 1 ? " anos" : " ano") + " e " +remainingDays + (remainingDays > 1 ? " dias." : " dia.");
            }
            return years + (years > 1 ? " anos." : " ano.");
        }else if(months > 0){
            remainingDays = remainingDays%7;
            if(remainingWeeks > 0){
                if(remainingDays > 0){
                    if(remainingHours > 0){
                        if(remainingMinutes > 0){
                            if(seconds > 0){
                                return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ")  + remainingDays + (remainingDays > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                            }
                            return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ")  + remainingDays + (remainingDays > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                        }
                        return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ")  + remainingDays + (remainingDays > 1 ? " dias" : " dia") + " e " + remainingHours + (remainingHours > 1 ? " horas." : " hora.");
                    }else if(remainingMinutes > 0){
                        if(seconds > 0){
                            return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ")  + remainingDays + (remainingDays > 1 ? " dias, " : " dia, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                        }
                        return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ")  + remainingDays + (remainingDays > 1 ? " dias" : " dia") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                    }else if(seconds > 0){
                        return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ")  + remainingDays + (remainingDays > 1 ? " dias" : " dia") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                    }
                    return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas" : " semana") + " e " + remainingDays + (remainingDays > 1 ? " dias." : " dia.");
                }else if(remainingHours > 0){
                    if(remainingMinutes > 0){
                        if(seconds > 0){
                            return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ") + remainingHours + (remainingHours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                        }
                        return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                    }else if(seconds > 0){
                        return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas" : " semana") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                    }
                    return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas" : " semana") + " e " + remainingHours + (remainingHours > 1 ? " horas." : " hora.");
                }else if(remainingMinutes > 0){
                    if(seconds > 0){
                        return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas, " : " semana, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                    }
                    return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas" : " semana") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                }else if(seconds > 0){
                    return months + (months > 1 ? " meses, " : " mês, ") + remainingWeeks + (remainingWeeks > 1 ? " semanas" : " semana") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return months + (months > 1 ? " meses" : " mês") + " e " + remainingWeeks + (remainingWeeks > 1 ? " semanas." : " semana.");
            }else if(remainingDays > 0){
                if(remainingHours > 0){
                    if(remainingMinutes > 0){
                        if(seconds > 0){
                            return months + (months > 1 ? " meses, " : " mês, ")  + remainingDays + (remainingDays > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                        }
                        return months + (months > 1 ? " meses, " : " mês, ")  + remainingDays + (remainingDays > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                    }
                    return months + (months > 1 ? " meses, " : " mês, ") + remainingDays + (remainingDays > 1 ? " dias" : " dia") + " e " + remainingHours + (remainingHours > 1 ? " horas." : " hora.");
                }
                return months + (months > 1 ? " meses" : " mês") + " e " + remainingDays + (remainingDays > 1 ? " dias." : " dia.");
            }else if(remainingHours > 0){
                if(remainingMinutes > 0){
                    if(seconds > 0){
                        return months + (months > 1 ? " meses, " : " mês, ")  + remainingHours + (remainingHours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                    }
                    return months + (months > 1 ? " meses, " : " mês, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                }else if(seconds > 0){
                    return months + (months > 1 ? " meses, " : " mês, ")  + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return months + (months > 1 ? " meses" : " mês") + " e " + remainingHours + (remainingHours > 1 ? " horas." : " hora.");
            }else if(remainingMinutes > 0){
                if(seconds > 0){
                    return months + (months > 1 ? " meses, " : " mês, ")  + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return months + (months > 1 ? " meses" : " mês") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
            }else if(seconds > 0){
                return months + (months > 1 ? " meses" : " mês")  + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
            }
            return months + (months > 1 ? " meses." : " mês.");
        }else if(weeks > 0){
            remainingDays = remainingDays%7;
            if(remainingDays > 0){
                if(remainingHours > 0){
                    if(remainingMinutes > 0){
                        if(seconds > 0){
                            return weeks + (weeks > 1 ? " semanas, " : " semana, ") + remainingDays + (remainingDays > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                        }
                        return weeks + (weeks > 1 ? " semanas, " : " semana, ") + remainingDays + (remainingDays > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                    }
                    return weeks + (weeks > 1 ? " semanas, " : " semana, ") + remainingDays + (remainingDays > 1 ? " dias" : " dia") + " e " + remainingHours + (remainingHours > 1 ? " horas." : " hora.");
                }
                return weeks + (weeks > 1 ? " semanas" : " semana") + " e " + remainingDays + (remainingDays > 1 ? " dias." : " dia.");
            }else if(remainingHours > 0){
                if(remainingMinutes > 0){
                    if(seconds > 0){
                        return weeks + (weeks > 1 ? " semanas, " : " semana, ") + remainingHours + (remainingHours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                    }
                    return weeks + (weeks > 1 ? " semanas, " : " semana, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                }else if(seconds > 0){
                    return weeks + (weeks > 1 ? " semanas, " : " semana, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return weeks + (weeks > 1 ? " semanas" : " semana") + " e " + remainingHours + (remainingHours > 1 ? " horas." : " hora.");
            }else if(remainingMinutes > 0){
                if(seconds > 0){
                    return weeks + (weeks > 1 ? " semanas, " : " semana, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return weeks + (weeks > 1 ? " semanas" : " semana") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
            }else if(seconds > 0){
                return weeks + (weeks > 1 ? " semanas" : " semana") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
            }
            return weeks + (weeks > 1 ? " semanas." : " semana.");
        }else if(days > 0){
            remainingHours = remainingHours%24;
            if(remainingHours > 0){
                if(remainingMinutes > 0){
                    if(seconds > 0){
                        return days + (days > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                    }
                    return days + (days > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
                }else if(seconds > 0){
                    return days + (days > 1 ? " dias, " : " dia, ") + remainingHours + (remainingHours > 1 ? " horas" : " hora") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return days + (days > 1 ? " dias" : " dia") + " e " + remainingHours + (remainingHours > 1 ? " horas." : " hora.");
            }else if(remainingMinutes > 0){
                if(seconds > 0){
                    return days + (days > 1 ? " dias, " : " dia, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return days + (days > 1 ? " dias" : " dia") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
            }else if(seconds > 0){
                return days + (days > 1 ? " dias" : " dia") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
            }
            return days + (days > 1 ? " dias." : " dia.");
        }else if(hours > 0 && hours < 24){
            if(remainingMinutes > 0){
                if(seconds > 0){
                    return hours + (hours > 1 ? " horas, " : " hora, ") + remainingMinutes + (remainingMinutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
                }
                return hours + (hours > 1 ? " horas" : " hora") + " e " + remainingMinutes + (remainingMinutes > 1 ? " minutos." : " minuto.");
            }else if(seconds > 0){
                return hours + (hours > 1 ? " horas" : " hora") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
            }
            return hours + (hours > 1 ? " horas." : " hora.");
        }


        if(remainingMinutes > 0){
            if(seconds > 0){
                return minutes + (minutes > 1 ? " minutos" : " minuto") + " e " + secondMessage + (seconds > 1 ? " segundos." : " segundo.");
            }
            return minutes + (minutes > 1 ? " minutos." : " minuto.");
        }
        return secondMessage + (seconds > 1 ? " segundos." : " segundo.");
    }
}