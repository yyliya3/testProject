import '../App.css'
import React, { PureComponent } from 'react'
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts'

export default function LineChartComponent(props){
    return (
        <LineChart
            width={1200}
            height={400}
            data={props.data}
            margin={{
                top: 5,
                right: 30,
                left: 20,
                bottom: 5,
            }}
        >
            <XAxis dataKey='time' />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line type='monotone' dataKey='cpu' stroke='#8884d8' activeDot={{ r: 8 }} />
            <Line type='monotone' dataKey='gpu' stroke='#82ca9d' />
            <Line type='monotone' dataKey='ram' stroke='#f8ae34' />
        </LineChart>
    )
}
